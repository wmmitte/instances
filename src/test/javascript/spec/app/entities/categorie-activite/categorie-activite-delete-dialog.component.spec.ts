/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InstancesTestModule } from '../../../test.module';
import { CategorieActiviteDeleteDialogComponent } from 'app/entities/categorie-activite/categorie-activite-delete-dialog.component';
import { CategorieActiviteService } from 'app/entities/categorie-activite/categorie-activite.service';

describe('Component Tests', () => {
    describe('CategorieActivite Management Delete Component', () => {
        let comp: CategorieActiviteDeleteDialogComponent;
        let fixture: ComponentFixture<CategorieActiviteDeleteDialogComponent>;
        let service: CategorieActiviteService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [CategorieActiviteDeleteDialogComponent]
            })
                .overrideTemplate(CategorieActiviteDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CategorieActiviteDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CategorieActiviteService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
