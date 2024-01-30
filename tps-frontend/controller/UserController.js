export class UserController {
    constructor() {
        $('#btn-user').click((event) => this.handleLoadAllUser(event));
        $('#btnSaveUser').click((event) => this.handleSaveUserValidation(event));
        $('#btnResetUser').click((event) => this.handleResetUserForm(event));
        $('#btnUpdateUser').click((event) => this.handleUpdateUser(event));
        $('#btnDeleteUser').click((event) => this.handleDeleteUser(event));
        $("#user_table_body").on("click", ".edit_user", (event) => this.handleSelectUser(event));
    }
    handleDeleteUser(event) {
        event.preventDefault();
        this.deleteUser();
    }
    deleteUser() {
        const userId = $('#txtManageUserId').val();
        if (!userId) {
            alert('please select user')
            return;
        }
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8090/auth/api/v1/auth/" + userId,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (response) => {
                this.handleLoadAllUserTable();
                this.resetFields();
                alert("delete success")
            },
            error: (error) => {
                alert("user not found")
            }
        })
    }
    handleUpdateUser(event) {
        event.preventDefault();
        const userId = $('#txtManageUserId').val();
        if (!userId) {
            alert('please select user')
            return;
        }

        let nicFrontImage = $('#txtUSerNicFront')[0].files[0];
        if (!nicFrontImage) {
            alert('please select nic front image')
            return;
        }

        let nicBackImage = $('#txtUSerNicBack')[0].files[0];
        if (!nicBackImage) {
            alert('please select nic back image')
            return;
        }


        // validation
        this.updateUser(nicFrontImage, nicBackImage);
    }
    updateUser(nicFrontImage, nicBackImage) {
        const userId = $('#txtManageUserId').val();
        const name = $('#txtUserName').val();
        const email = $('#txtUserEmail').val();
        const nic = $('#txtUserNic').val();
        const address = $('#txtUserAddress').val();
        const userName = $('#txtUserUsername').val();
        const password = $('#txtUserPassword').val();
        const phone = $('#txtUserPhone').val();



        var formData = new FormData();

        formData.append('name', name);
        formData.append('email', email);
        formData.append('nic', nic);
        formData.append('address', address);
        formData.append('username', userName);
        formData.append('password', password);
        formData.append('userRole', "USER");
        formData.append('phone', phone);
        formData.append('nicFrontImage', nicFrontImage);
        formData.append('nicBackImage', nicBackImage);

        $.ajax({
            type: "PUT",
            url: "http://localhost:8090/auth/api/v1/auth/" + userId,
            data: formData,
            contentType: false,
            processData: false,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (data) => {
                this.handleLoadAllUserTable();
                this.resetFields();
                alert('success')
            },
            error: (error) => {
                alert(error)
            }
        });
    }
    handleResetUserForm(event) {
        event.preventDefault();
        this.resetFields();
    }
    resetFields() {
        $('#txtUserName').val("");
        $('#txtUserNic').val("");
        $('#txtUserEmail').val("");
        $('#txtUserAddress').val("");
        $('#txtUserUsername').val("");
        $('#txtUserPassword').val("");
        $('#txtUserPhone').val("");
        $('#txtUSerNicFront').val("");
        $('#txtUSerNicBack').val("");
        $('#txtManageUserId').val("");
        $('#imgNicFront').val("");
        $('#imgNicBack').val("");

    }
    handleSelectUser(event) {
        event.preventDefault();
        const row = $(event.currentTarget.closest('tr'));
        const userId = $(event.target.closest('tr')).data('user-id');

        const name = row.find('td:nth-child(1)').text();
        const email = row.find('td:nth-child(3)').text();
        const nic = row.find('td:nth-child(2)').text();
        const address = row.find('td:nth-child(4)').text();
        const username = row.find('td:nth-child(5)').text();
        const phone = row.find('td:nth-child(6)').text();
        const images = row.find('img');


        var nicFrontImage = $(images[0]).attr('src').split(' ')[1]
        var nicBackImage = $(images[1]).attr('src').split(' ')[1]

        $('#txtManageUserId').val(userId);
        $('#txtUserName').val(name);
        $('#txtUserNic').val(nic);
        $('#txtUserEmail').val(email);
        $('#txtUserAddress').val(address);
        $('#txtUserUsername').val(username);
        $('#txtUserPhone').val(phone);
        $('#imgNicFront').val(nicFrontImage);
        $('#imgNicBack').val(nicBackImage);

    }
    handleSaveUserValidation(event) {
        event.preventDefault();

        const userId = $('#txtManageUserId').val();
        const name = $('#txtUserName').val();
        const email = $('#txtUserEmail').val();
        const nic = $('#txtUserNic').val();
        const address = $('#txtUserAddress').val();
        const userName = $('#txtUserUsername').val();
        const password = $('#txtUserPassword').val();
        const phone = $('#txtUserPhone').val();

        const nicFrontImage = $('#txtUSerNicFront')[0].files[0];
        const nicBackImage = $('#txtUSerNicBack')[0].files[0];

        var formData = new FormData();

        formData.append('name', name);
        formData.append('email', email);
        formData.append('nic', nic);
        formData.append('address', address);
        formData.append('username', userName);
        formData.append('password', password);
        formData.append('userRole', "USER");
        formData.append('phone', phone);
        formData.append('nicFrontImage', nicFrontImage);
        formData.append('nicBackImage', nicBackImage);

        this.handleSaveUser(formData);
    }
    handleSaveUser(user) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/auth/api/v1/auth/register",
            data: user,
            contentType: false,
            processData: false,
            success: (data) => {
                this.handleLoadAllUserTable();
                this.resetFields();
                alert('success')
            },
            error: (error) => {
                alert(error)
            }
        });
    }
    handleLoadAllUser(event) {
        event.preventDefault();
        this.handleLoadAllUserTable();
    }
    handleLoadAllUserTable() {
        $('#user_table_body').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/auth/api/v1/auth",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (users) => {
                $('#user_table_body').append(users?.map(this.renderUserRow).join(''));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    renderUserRow(user) {
        return `
            <tr data-user-id="${user.userId}">
                <td class="col-lg-2">${user.name}</td>
                <td class="col-lg-1">${user.nic}</td>
                <td class="col-lg-2">${user.email}</td>
                <td class="col-lg-2">${user.address}</td>
                <td class="col-lg-1">${user.username}</td>
                <td class="col-lg-1">${user.phone}</td>
                <td class="col-lg-1 text-center">
                    <img class="imgFront" src="data:image/jpeg;base64, ${user.nicFrontImage}" width="80%">
                </td> 
                <td class="col-lg-1 text-center">
                    <img class="imgBack" src="data:image/jpeg;base64, ${user.nicBackImage}" width="80%">
                </td>
                <td class="col-lg-1">
                    <a class="edit_icon edit_user" href="" >
                    <i class='bx bx-edit'></i>
                </a></td>
            </tr>
        `;
    }
}
new UserController();