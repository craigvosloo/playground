--Employees
INSERT INTO employee (first_name, last_name, annual_salary, pension_contribution, start_date) values ('Andrew', 'Baker', 60050, 9, parsedatetime('01-03-2016', 'dd-MM-yyyy')); 
INSERT INTO employee (first_name, last_name, annual_salary, pension_contribution, start_date) values ('Chris', 'Davies', 120000, 10, parsedatetime('01-05-2016', 'dd-MM-yyyy'));

--Tax
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (0, 0, 0, 0); 
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (18201, 18200, 0, 19);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (37001, 37000, 3572, 32.5);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (80001, 80000, 17547, 37);
INSERT INTO tax (salary_from, base_salary, base_salary_tax, additional_rate) values (180001, 80000, 17547, 37);

--Payslips
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-03-2016', 'dd-MM-yyyy'), 5004, 921.94, 4082.06, 450.36, (select id from employee where last_name = 'Baker'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-04-2016', 'dd-MM-yyyy'), 5004, 921.94, 4082.06, 450.36, (select id from employee where last_name = 'Baker'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-05-2016', 'dd-MM-yyyy'), 5004, 921.94, 4082.06, 450.36, (select id from employee where last_name = 'Baker'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-06-2016', 'dd-MM-yyyy'), 5004, 921.94, 4082.06, 450.36, (select id from employee where last_name = 'Baker'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-07-2016', 'dd-MM-yyyy'), 5004, 921.94, 4082.06, 450.36, (select id from employee where last_name = 'Baker'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-05-2016', 'dd-MM-yyyy'), 10000, 2695.58, 7304.42, 1000, (select id from employee where last_name = 'Davies'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-06-2016', 'dd-MM-yyyy'), 10000, 2695.58, 7304.42, 1000, (select id from employee where last_name = 'Davies'), false);
INSERT INTO payslip (payslip_date, gross_income, income_tax, net_income, pension_contribution, employee_id, prorated) values (parsedatetime('01-07-2016', 'dd-MM-yyyy'), 10000, 2695.58, 7304.42, 1000, (select id from employee where last_name = 'Davies'), false);