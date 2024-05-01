import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { User } from '../../models/user.model';
import { ApiService } from '../../services/api.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-registration-list',
  templateUrl: './registration-list.component.html',
  styleUrl: './registration-list.component.css',
})
export class RegistrationListComponent implements OnInit {
  public users!: User[];
  dataSource!: MatTableDataSource<User>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'email',
    'mobile',
    'bmiResult',
    'gender',
    'package',
    'enquiryDate',
    'action',
  ];

  constructor(private api: ApiService, private router: Router) {}

  getUsers() {
    this.api.getRegisteredUser().subscribe({
      next: (res) => {
        console.log('res is', res);
        this.users = res;
        this.dataSource = new MatTableDataSource(this.users);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  ngOnInit(): void {
    this.getUsers();
  }

  edit(id: number) {
    this.router.navigate(['update', id]);
  }

  deleteUser(id: number) {
    this.api.deleteRegistered(id).subscribe((res) => {
      //To display the updated value we will againa all the getUsers function
      this.getUsers();
    });
  }

  applyFilter(event: any) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }
}
