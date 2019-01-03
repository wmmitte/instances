import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';
import { CategorieActiviteService } from './categorie-activite.service';

@Component({
    selector: 'jhi-categorie-activite-update',
    templateUrl: './categorie-activite-update.component.html'
})
export class CategorieActiviteUpdateComponent implements OnInit {
    categorieActivite: ICategorieActivite;
    isSaving: boolean;

    constructor(protected categorieActiviteService: CategorieActiviteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ categorieActivite }) => {
            this.categorieActivite = categorieActivite;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.categorieActivite.id !== undefined) {
            this.subscribeToSaveResponse(this.categorieActiviteService.update(this.categorieActivite));
        } else {
            this.subscribeToSaveResponse(this.categorieActiviteService.create(this.categorieActivite));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieActivite>>) {
        result.subscribe((res: HttpResponse<ICategorieActivite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
