import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHistorique } from 'app/shared/model/historique.model';

@Component({
    selector: 'jhi-historique-detail',
    templateUrl: './historique-detail.component.html'
})
export class HistoriqueDetailComponent implements OnInit {
    historique: IHistorique;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ historique }) => {
            this.historique = historique;
        });
    }

    previousState() {
        window.history.back();
    }
}
