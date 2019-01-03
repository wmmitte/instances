/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InstancesTestModule } from '../../../test.module';
import { CategorieActiviteUpdateComponent } from 'app/entities/categorie-activite/categorie-activite-update.component';
import { CategorieActiviteService } from 'app/entities/categorie-activite/categorie-activite.service';
import { CategorieActivite } from 'app/shared/model/categorie-activite.model';

describe('Component Tests', () => {
    describe('CategorieActivite Management Update Component', () => {
        let comp: CategorieActiviteUpdateComponent;
        let fixture: ComponentFixture<CategorieActiviteUpdateComponent>;
        let service: CategorieActiviteService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [CategorieActiviteUpdateComponent]
            })
                .overrideTemplate(CategorieActiviteUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CategorieActiviteUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorieActiviteService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CategorieActivite(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.categorieActivite = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new CategorieActivite();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.categorieActivite = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
