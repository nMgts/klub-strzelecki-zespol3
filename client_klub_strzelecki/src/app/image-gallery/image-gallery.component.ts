import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {Imagedata} from "../interfaces/imagedata";
import {HttpErrorResponse} from "@angular/common/http";
import {UsersService} from "../services/users.service";

@Component({
  selector: 'app-image-gallery',
  templateUrl: './image-gallery.component.html',
  styleUrls: ['./image-gallery.component.css']
})
export class ImageGalleryComponent implements OnInit {
  images: { id: number; name: string; url: SafeUrl }[] = [];
  showAddImageForm = false;
  newImageName = '';
  errorMessage: string = '';
  selectedFile: File | null = null;

  isAdmin:boolean = false;

  constructor(private imageService: ImageService, private sanitizer: DomSanitizer, private userService: UsersService) { }

  ngOnInit(): void {
    this.isAdmin = this.userService.isAdmin();
    this.loadImages();
  }

  loadImages(): void {
    this.imageService.getAllImages().subscribe(
      (images: Imagedata[]) => {
        this.images = images.map(image => ({
          id: image.id,
          name: image.name,
          url: this.sanitizer.bypassSecurityTrustUrl(`data:image/jpeg;base64,${image.imageData}`)
        }));
      },
      error => {
        console.error('Błąd podczas ładowania obrazów', error);
      }
    );
  }

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }


  onAddImage(): void {
    if (this.newImageName && this.selectedFile) {
      this.imageService.addImage(this.newImageName, this.selectedFile).subscribe()
          this.showAddImageForm = false;
          this.newImageName = '';
          this.selectedFile = null;
    }
    this.loadImages();
  }

  onDeleteImage(id: number): void {
    if (confirm('Czy na pewno chcesz usunąć to zdjęcie?')) {
      this.imageService.deleteImage(id).subscribe(
        () => {
          this.images = this.images.filter(image => image.id !== id);
          console.log('Zdjęcie usunięte');
        },
        (error: HttpErrorResponse) => {
          console.error('Błąd podczas usuwania zdjęcia', error);
        }
      );
    }
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
