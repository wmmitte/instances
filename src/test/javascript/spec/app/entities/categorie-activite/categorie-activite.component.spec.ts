/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InstancesTestModule } from '../../../test.module';
import { CategorieActiviteComponent } from 'app/entities/categorie-activite/categorie-activite.component';
import { CategorieActiviteService } from 'app/entities/categorie-activite/categorie-activite.service';
import { CategorieActivite } from 'app/shared/model/categorie-activite.model';

describe('Component Tests', () => {
    describe('CategorieActivite Management Component', () => {
        let comp: CategorieActiviteComponent;
        let fixture: ComponentFixture<CategorieActiviteComponent>;
        let service: CategorieActiviteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [CategorieActiviteComponent],
                providers: []
            })
                .overrideTemplate(CategorieActiviteComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CategorieActiviteComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorieActiviteService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new CategorieActivite(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.categorieActivites[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
