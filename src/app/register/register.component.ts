import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';  // Import HttpClient here
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';  // Import Router here

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],  // No need for HttpClientModule here
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  apiUrl = 'http://localhost:3000/users'; // JSON Server API

  // Inject the Router service
  constructor(
    private fb: FormBuilder, 
    private http: HttpClient,  // HttpClient will be injected here
    private router: Router  // ✅ Inject Router here
  ) {
    this.registerForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      address: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      confirmPassword: ['', Validators.required]
    }, { validators: this.passwordMatchValidator });
  }

  passwordMatchValidator(form: FormGroup) {
    return form.get('password')?.value === form.get('confirmPassword')?.value
      ? null : { mismatch: true };
  }

  onSubmit() {
    if (this.registerForm.valid) {
      this.http.post(this.apiUrl, this.registerForm.value).subscribe(
        (response) => {
          console.log("User registered successfully:", response);
          alert("Registration successful!");
          this.router.navigate(['/login']);  // ✅ Use router.navigate after registration
        },
        (error: any) => {  // Handle error
          console.error("Error registering user:", error);
        }
      );
    } else {
      console.log("Invalid Form");
    }
  }
}
