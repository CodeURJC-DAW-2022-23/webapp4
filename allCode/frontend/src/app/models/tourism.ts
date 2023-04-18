import { Destination } from "./destination";

export interface Tourism{
    id:number;
    //nameDestination:string;
    nameTourism:string;
    contentTourism:string;
    destination:Destination;
    tourismImageFile:Blob;
    imageTourism:string;
}