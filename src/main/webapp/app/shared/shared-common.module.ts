import { NgModule } from '@angular/core';

import { InstancesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [InstancesSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [InstancesSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class InstancesSharedCommonModule {}
