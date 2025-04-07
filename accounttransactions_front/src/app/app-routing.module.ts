import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { ClientComponent } from './client/client.component';
import { AccountComponent } from './account/account.component';
import { TransactionComponent } from './transaction/transaction.component';
import { ReportComponent } from './report/report.component';

const routes: Routes = [
  { path: '', component: MainComponent },
  { path: 'client', component: ClientComponent },
  { path: 'account', component: AccountComponent },
  { path: 'transaction', component: TransactionComponent },
  { path: 'report', component: ReportComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

