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

  constructor(private imageService: ImageService) { }

  ngOnInit(): void {
    this.imageService.getAllImages().subscribe(data => {
      this.images = data;
    });
  }
}
