import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ApiService } from '../../services/api.service';
import { NgToastService } from 'ng-angular-popup';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-create-registration',
  templateUrl: './create-registration.component.html',
  styleUrl: './create-registration.component.css',
})
export class CreateRegistrationComponent implements OnInit {
  public packages: string[] = ['Monthly', 'Quarterly', 'Yearly'];
  public genders: string[] = ['Male', 'Female'];
  public importantList: string[] = [
    'Toxic Fat reduction',
    'Energy and Endurance',
    'Building Lean Muscle',
    'Healthier Digestive System',
    'Sugar Craving Body',
    'Fitness',
  ];

  public registerForm!: FormGroup;
  public userIdToUpdate!: number;
  public isUpdateActive: boolean = false;

  constructor(
    private fb: FormBuilder,
    private api: ApiService,
    private toastService: NgToastService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: [''],
      lastName: [''],
      email: [''],
      mobile: [''],
      weight: [''],
      height: [''],
      bmi: [''],
      bmiResult: [''],
      gender: [''],
      requireTrainer: [''],
      package: [''],
      important: [''],
      haveGymBefore: [''],
      enquiryDate: [''],
    });

    this.registerForm.controls['height'].valueChanges.subscribe((res) => {
      this.calculateBMI(res);
    });

    this.activatedRoute.params.subscribe((val) => {
      console.log('This is going inside activated routes');
      console.log('This is the val inside activated routes:', val);
      this.userIdToUpdate = val['id'];
      this.isUpdateActive = !!this.userIdToUpdate; // Set isUpdateActive to true if userIdToUpdate is not null or undefined
      if (this.isUpdateActive) {
        this.api.getRegisteredUserId(this.userIdToUpdate).subscribe((res) => {
          this.fillFormToUpdate(res);
        });
      }
    });
  }

  fillFormToUpdate(user: User) {
    this.registerForm.setValue({
      firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      mobile: user.mobile,
      weight: user.weight,
      height: user.height,
      bmi: user.bmi,
      bmiResult: user.bmiResult,
      gender: user.gender,
      requireTrainer: user.requireTrainer,
      package: user.package,
      important: user.important,
      haveGymBefore: user.haveGymBefore,
      enquiryDate: user.enquiryDate,
    });
  }

  public submit() {
    console.log('This is inside submit button');
    console.log(this.registerForm.value);
    this.api.postRegistration(this.registerForm.value).subscribe((res) => {
      this.toastService.success({
        detail: 'Success',
        summary: 'Enquiry Added',
        duration: 3000,
      });
      this.router.navigate(['list']);
      this.registerForm.reset();
    });
  }

  public update() {
    console.log('This is inside update button');
    console.log(this.registerForm.value);
    this.api
      .updateRegisterUser(this.registerForm.value, this.userIdToUpdate)
      .subscribe((res) => {
        this.toastService.success({
          detail: 'Success',
          summary: 'Enquiry Updated',
          duration: 3000,
        });
        this.registerForm.reset();
      });
  }

  calculateBMI(heightValue: number) {
    const weight = this.registerForm.value.weight;
    const height = heightValue;

    if (height === 0) {
      // Handle the case where height is zero
      // For example, you can set BMI and BMI result to appropriate default values
      this.registerForm.controls['bmi'].patchValue(0);
      this.registerForm.controls['bmiResult'].patchValue(
        'Height cannot be zero'
      );
      return;
    }

    const bmi = weight / (height * height);
    this.registerForm.controls['bmi'].patchValue(bmi);
    switch (true) {
      case bmi < 18.5:
        this.registerForm.controls['bmiResult'].patchValue('Underweight');
        break;
      case bmi >= 18.5 && bmi < 25:
        this.registerForm.controls['bmiResult'].patchValue('Normal');
        break;
      case bmi >= 25 && bmi < 30:
        this.registerForm.controls['bmiResult'].patchValue('Overweight');
        break;
      default:
        this.registerForm.controls['bmiResult'].patchValue('Obese');
        break;
    }
  }
}
