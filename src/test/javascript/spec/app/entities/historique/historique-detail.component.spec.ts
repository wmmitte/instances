/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InstancesTestModule } from '../../../test.module';
import { HistoriqueDetailComponent } from 'app/entities/historique/historique-detail.component';
import { Historique } from 'app/shared/model/historique.model';

describe('Component Tests', () => {
    describe('Historique Management Detail Component', () => {
        let comp: HistoriqueDetailComponent;
        let fixture: ComponentFixture<HistoriqueDetailComponent>;
        const route = ({ data: of({ historique: new Historique(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [HistoriqueDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HistoriqueDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HistoriqueDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.historique).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
