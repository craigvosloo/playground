--Employees
INSERT INTO employee (first_name, last_name, annual_salary, pension_contribution, payment_start_date) values ('Andrew', 'Baker', 60050, 9, parsedatetime('01-03-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ')); 
INSERT INTO employee (first_name, last_name, annual_salary, pension_contribution, payment_start_date) values ('Chris', 'Davies', 120000, 10, parsedatetime('01-05-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'));

--Tax
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (0, 0, 0, 0); 
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (18201, 18200, 0, 19);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (37001, 37000, 3572, 32.5);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (80001, 80000, 17547, 37);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (180001, 80000, 17547, 37);

--Payslips
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-03-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 5004, 922, 4082, 450, (select id from employee where last_name = 'Baker'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-04-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 5004, 922, 4082, 450, (select id from employee where last_name = 'Baker'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-05-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 5004, 922, 4082, 450, (select id from employee where last_name = 'Baker'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-06-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 5004, 922, 4082, 450, (select id from employee where last_name = 'Baker'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-07-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 5004, 922, 4082, 450, (select id from employee where last_name = 'Baker'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-05-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 10000, 2696, 7304, 1000, (select id from employee where last_name = 'Davies'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-06-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 10000, 2696, 7304, 1000, (select id from employee where last_name = 'Davies'));
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id) values (parsedatetime('01-07-2016 00:00:00+0000', 'dd-MM-yyyy hh:mm:ssZ'), 10000, 2696, 7304, 1000, (select id from employee where last_name = 'Davies'));