/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { InstancesTestModule } from '../../../test.module';
import { HistoriqueUpdateComponent } from 'app/entities/historique/historique-update.component';
import { HistoriqueService } from 'app/entities/historique/historique.service';
import { Historique } from 'app/shared/model/historique.model';

describe('Component Tests', () => {
    describe('Historique Management Update Component', () => {
        let comp: HistoriqueUpdateComponent;
        let fixture: ComponentFixture<HistoriqueUpdateComponent>;
        let service: HistoriqueService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [HistoriqueUpdateComponent]
            })
                .overrideTemplate(HistoriqueUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(HistoriqueUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HistoriqueService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Historique(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.historique = entity;
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
                    const entity = new Historique();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.historique = entity;
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
