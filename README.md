# "Lease" is a platform for house renting.    
---
#### This project uses 
* #### Spring + SpringMVC + SpringBoot + Mybatis + MybatisPlus
* #### MySQL as the main dababase, and Redis as memory level DB to improve performance
* #### Aliyun cloud service for SMS services
* #### Jwt for login control
* #### Minio for resource storage          
---
#### This project consists of two main aspects: Admin page and User page
* Admin page
  ![admin_login](https://github.com/1157350959/lease/blob/master/template_pics/admin_login.jpeg)
    * System-wise:
  ![admin_systemuser_management](https://github.com/1157350959/lease/blob/master/template_pics/admin_systemuser_management.PNG)
        * manage admin users, admin user job titles, admin user basic information, admin user status
        * manage general job titles 
    * Apartment-wise:
  ![admin_room_management](https://github.com/1157350959/lease/blob/master/template_pics/admin_room_management.PNG)
        * manage current apartment information, modify, delete, or add new apartments; can be filtered by province, and then city, and then district; paging enabled
        * manage current room information(within certain apartment), modify, delete, or add new rooms; can be filtered by province, and then city, and then district, and then apartment; paging enbaled
        * miscellaneous: configure apartment/room facility info, apartment/room label, room related attributes, fees, lease terms, and payment types
    * Lease-wise:
  ![admin_view_appointment](https://github.com/1157350959/lease/blob/master/template_pics/admin_view_appointment.PNG)
        * manage appointments information, one can make a appointment with supervisiors when interested in certain room, supervisors will be able to view appointment status, like pending-appointment, or done-appointment
        * lease management, that is all leases among all users, allowing admins to modify, delete, or add new leases; scheduled task every day to update all leases' status(from signed to normal terminated)
    * User-wise:
  ![admin_user_management](https://github.com/1157350959/lease/blob/master/template_pics/admin_user_management.PNG)
        * manage all users using the platform, having the status bar indicating whether the user is enabled 
* User page: for **logged in** platform users to perform
    * Accommodation searching, can be filtered by location, price, payment type, and can be sorted by price
  ![app_searching_apartment](https://github.com/1157350959/lease/blob/master/template_pics/app_searching_apartment.jpeg)
    * View history
    * Check appointment information
    * Show current lease, can renew lease or terminate the lease early
  ![app_personal_lease](https://github.com/1157350959/lease/blob/master/template_pics/app_personal_lease.jpeg)
  ![app_personal](https://github.com/1157350959/lease/blob/master/template_pics/app_personal.jpeg)


#### Note: 
* ##### front end projects on both pages were provided and written in Vue.js
* ##### left top distorted icon and cut off title in admin page due to browser translation error
