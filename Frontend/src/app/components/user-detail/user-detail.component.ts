import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user.model';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrl: './user-detail.component.css',
})
export class UserDetailComponent implements OnInit {
  public userID!: number;
  userDetails!: User;
  constructor(private activedRoute: ActivatedRoute, private api: ApiService) {}

  ngOnInit(): void {
    this.activedRoute.params.subscribe((val) => {
      console.log('Inside actived params of detail page');
      console.log('Consoling the val:', val);
      this.userID = val['id'];
      this.api.getRegisteredUserId(this.userID).subscribe((res) => {
        this.userDetails = res;
      });
    });
  }
}
