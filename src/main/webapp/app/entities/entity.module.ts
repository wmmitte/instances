import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { InstancesCategorieActiviteModule } from './categorie-activite/categorie-activite.module';
import { InstancesClientModule } from './client/client.module';
import { InstancesProjetModule } from './projet/projet.module';
import { InstancesHistoriqueModule } from './historique/historique.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        InstancesCategorieActiviteModule,
        InstancesClientModule,
        InstancesProjetModule,
        InstancesHistoriqueModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class InstancesEntityModule {}
