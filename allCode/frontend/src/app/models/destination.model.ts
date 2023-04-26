import { Catering } from "./catering.model";
import { House } from "./house.model";
import { Review } from "./review.model";
import { Tourism } from "./tourism.model";

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