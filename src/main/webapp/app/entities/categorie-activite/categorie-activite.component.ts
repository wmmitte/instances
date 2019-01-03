import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';
import { AccountService } from 'app/core';
import { CategorieActiviteService } from './categorie-activite.service';

@Component({
    selector: 'jhi-categorie-activite',
    templateUrl: './categorie-activite.component.html'
})
export class CategorieActiviteComponent implements OnInit, OnDestroy {
    categorieActivites: ICategorieActivite[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected categorieActiviteService: CategorieActiviteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.categorieActiviteService.query().subscribe(
            (res: HttpResponse<ICategorieActivite[]>) => {
                this.categorieActivites = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCategorieActivites();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICategorieActivite) {
        return item.id;
    }

    registerChangeInCategorieActivites() {
        this.eventSubscriber = this.eventManager.subscribe('categorieActiviteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
