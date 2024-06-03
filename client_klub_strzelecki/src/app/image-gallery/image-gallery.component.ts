import { Component, OnInit } from '@angular/core';
import { ImageService } from '../services/image.service';
import { Imagedata } from '../interfaces/imagedata';

@Component({
  selector: 'app-image-gallery',
  templateUrl: './image-gallery.component.html',
  styleUrls: ['./image-gallery.component.css']
})
export class ImageGalleryComponent implements OnInit {
  images: Imagedata[] = [];
  errorMessage: string = '';

  constructor(private imageService: ImageService) { }

  async loadImages() {
    try {
      const token: any = localStorage.getItem('token');
      const response = await this.imageService.getAllImages(token);
      if (response) {
        this.images = response;
      } else {
        this.showError('No images found.');
      }
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  ngOnInit(): void {
    this.loadImages();
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = ''
    }, 3000)
  }
}
