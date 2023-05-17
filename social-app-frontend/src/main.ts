import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { environment } from '@environments/environment';
import { AppModule } from './app/app.module';
import { enableProdMode } from '@angular/core';

if (environment.production) {
    enableProdMode();
}

console.log(environment.httpBackend);

platformBrowserDynamic().bootstrapModule(AppModule)
    .catch(err => console.error(err));
