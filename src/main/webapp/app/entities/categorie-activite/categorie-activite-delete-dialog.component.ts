import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieActivite } from 'app/shared/model/categorie-activite.model';
import { CategorieActiviteService } from './categorie-activite.service';

@Component({
    selector: 'jhi-categorie-activite-delete-dialog',
    templateUrl: './categorie-activite-delete-dialog.component.html'
})
export class CategorieActiviteDeleteDialogComponent {
    categorieActivite: ICategorieActivite;

    constructor(
        protected categorieActiviteService: CategorieActiviteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categorieActiviteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'categorieActiviteListModification',
                content: 'Deleted an categorieActivite'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-categorie-activite-delete-popup',
    template: ''
})
export class CategorieActiviteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ categorieActivite }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CategorieActiviteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.categorieActivite = categorieActivite;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
