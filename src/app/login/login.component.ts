import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';  // ✅ Import Router
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],  // ✅ Add HttpClientModule
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  apiUrl = 'http://localhost:3000/users'; // JSON Server API

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {  // ✅ Inject Router
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.http.get<any[]>(this.apiUrl).subscribe(users => {
        const user = users.find(u => u.email === this.loginForm.value.email && u.password === this.loginForm.value.password);

        if (user) {
          console.log("Login Successful:", user);
          alert("Login successful!");
          this.router.navigate(['/home']);  // ✅ Redirect to Home Page
        } else {
          console.log("Invalid Credentials");
          alert("Invalid email or password!");
        }
      });
    } else {
      console.log("Invalid Form");
    }
  }

  navigateToRegister() {
    this.router.navigate(['/register']);  // ✅ Function to redirect to Register Page
  }
}
