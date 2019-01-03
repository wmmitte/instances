import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';

@Component({
    selector: 'jhi-categorie-activite-detail',
    templateUrl: './categorie-activite-detail.component.html'
})
export class CategorieActiviteDetailComponent implements OnInit {
    categorieActivite: ICategorieActivite;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ categorieActivite }) => {
            this.categorieActivite = categorieActivite;
        });
    }

    previousState() {
        window.history.back();
    }
}
