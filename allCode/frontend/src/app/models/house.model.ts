import { Destination } from './destination.model';
import { Purchase } from './purchase.model';

export interface House {
    id: number;
    nameHouse: string;
    contentHouse: string;
    price: number;
    houseImageFile: Blob;
    destination:Destination;
    //imagesHouse:string;
    //hostImageFile: Blob;
    //hostImage:string;
    purchase: Purchase;
    //mapa Link?
}