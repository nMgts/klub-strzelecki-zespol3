import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import {DomSanitizer, SafeUrl} from "@angular/platform-browser";
import {Imagedata} from "../interfaces/imagedata";

@Component({
  selector: 'app-image-gallery',
  templateUrl: './image-gallery.component.html',
  styleUrls: ['./image-gallery.component.css']
})
export class ImageGalleryComponent implements OnInit {
  images: { id: number; name: string; url: SafeUrl }[] = [];
  errorMessage: string = '';

  constructor(private imageService: ImageService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
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

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
