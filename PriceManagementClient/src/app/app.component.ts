import { Component } from '@angular/core';
import { User } from './model/user';
import { KeycloakService } from './services/keycloak.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  profile: User;

    constructor(private keycloakService: KeycloakService) {
    }

    public ngOnInit(): void {
        this.profile = this.keycloakService.getUser();
    }

    public isManager(): boolean {
        return this.keycloakService.hasAnyRole(['manager']);
    }

    public isAdmin(): boolean {
        return this.keycloakService.hasAnyRole(['admin']);
    }
    
    public logout() {
		this.keycloakService.logout();
    }
	
	public getUsername(): string {
		return this.keycloakService.getUser().username;
	}

}
