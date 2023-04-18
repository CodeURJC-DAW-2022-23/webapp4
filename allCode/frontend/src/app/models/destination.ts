import { Catering } from "./catering";
import { House } from "./house";
import { Review } from "./review";
import { Tourism } from "./tourism";

export interface Destination {
    id: number;
    titleImageFile: Blob;
    titleImage: string;
    contentDestination: string;
    nameDestination: string;
    house: House;
    review: Review;
    catering : Catering;
    tourism : Tourism;
    price : number;
    
}