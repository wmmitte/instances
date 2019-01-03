/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InstancesTestModule } from '../../../test.module';
import { CategorieActiviteDetailComponent } from 'app/entities/categorie-activite/categorie-activite-detail.component';
import { CategorieActivite } from 'app/shared/model/categorie-activite.model';

describe('Component Tests', () => {
    describe('CategorieActivite Management Detail Component', () => {
        let comp: CategorieActiviteDetailComponent;
        let fixture: ComponentFixture<CategorieActiviteDetailComponent>;
        const route = ({ data: of({ categorieActivite: new CategorieActivite(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [CategorieActiviteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CategorieActiviteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CategorieActiviteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.categorieActivite).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
