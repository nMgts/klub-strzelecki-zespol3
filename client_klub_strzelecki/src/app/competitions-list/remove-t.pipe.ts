// remove-t.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'removeT'
})
export class RemoveTPipe implements PipeTransform {
  transform(value: string): string {
    return value ? value.replace('T', ' ') : value;
  }
}
