import { Destination } from "./destination.model";

export interface Tourism{
    id:number;
    //nameDestination:string;
    nameTourism:string;
    contentTourism:string;
    destination:Destination;
    tourismImageFile:Blob;
    imageTourism:string;
}