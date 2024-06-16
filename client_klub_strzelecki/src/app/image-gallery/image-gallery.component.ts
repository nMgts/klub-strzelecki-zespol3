import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';

@Component({
  selector: 'app-image-gallery',
  templateUrl: './image-gallery.component.html',
  styleUrls: ['./image-gallery.component.css']
})
export class ImageGalleryComponent implements OnInit {
  images: any[] = [];
  errorMessage: string = '';

  constructor(private imageService: ImageService) { }

  ngOnInit(): void {
    this.loadImages();
  }

  loadImages() {
    this.imageService.getAllImages().subscribe({
      next: data => {
        this.images = data;
        this.images.forEach(image => {
          this.loadImage(image);
        });
      },
      error: err => {
        this.showError(err.message);
      }
    });
  }

  loadImage(image: any) {
    this.imageService.getImage(image.id).subscribe({
      next: imageBlob => {
        const reader = new FileReader();
        reader.onload = () => {
          image.imageSrc = reader.result;
        };
        reader.readAsDataURL(imageBlob);
      },
      error: err => {
        this.showError(err.message);
      }
    });
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
