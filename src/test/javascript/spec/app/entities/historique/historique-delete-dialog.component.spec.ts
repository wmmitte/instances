/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InstancesTestModule } from '../../../test.module';
import { HistoriqueDeleteDialogComponent } from 'app/entities/historique/historique-delete-dialog.component';
import { HistoriqueService } from 'app/entities/historique/historique.service';

describe('Component Tests', () => {
    describe('Historique Management Delete Component', () => {
        let comp: HistoriqueDeleteDialogComponent;
        let fixture: ComponentFixture<HistoriqueDeleteDialogComponent>;
        let service: HistoriqueService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InstancesTestModule],
                declarations: [HistoriqueDeleteDialogComponent]
            })
                .overrideTemplate(HistoriqueDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HistoriqueDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(HistoriqueService);
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
