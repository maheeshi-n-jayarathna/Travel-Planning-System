export class CustomerController {
    constructor() {
        $('#btn-customer').click((event) => this.handleLoadAllCustomer(event));
        $('#btnSaveCustomer').click((event) => this.handleSaveCustomerValidation(event));
        $('#btnResetCustomer').click((event) => this.handleResetCustomerForm(event));
        $('#btnUpdateCustomer').click((event) => this.handleUpdateCustomer(event));
        $('#btnDeleteCustomer').click((event) => this.handleDeleteCustomer(event));
        $("#customer_table_body").on("click", ".edit_customer", (event) => this.handleSelectCustomer(event));
    }
    handleDeleteCustomer(event) {
        event.preventDefault();
        this.deleteCustomer();
    }
    deleteCustomer() {
        const userId = $('#txtManageCustomerId').val();
        if (!userId) {
            alert('please select customer')
            return;
        }
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8090/auth/api/v1/auth/" + userId,
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (response) => {
                this.handleLoadAllCustomerTable();
                this.resetFields();
                alert("delete success")
            },
            error: (error) => {
                alert("customer not found or customer have booking")
            }
        })
    }
    handleUpdateCustomer(event) {
        event.preventDefault();
        const userId = $('#txtManageCustomerId').val();
        if (!userId) {
            alert('please select customer')
            return;
        }

        // validation

        const profileImg = document.getElementById('manage-cropped-image');
        if (!profileImg) {
            alert('please select nic front image')
            return;
        }

        const password = $('#txtManageCustomerPassword').val();
        if (!password) {
            alert('please enter new or old pasword for update user')
            return;
        }
        this.updateCustomer(profileImg);
    }
    updateCustomer(profile) {
        const userId = $('#txtManageCustomerId').val();
        const name = $('#txtManageCustomerName').val();
        const email = $('#txtManageCustomerEmail').val();
        const nic = $('#txtManageCustomerNic').val();
        const address = $('#txtManageCustomerAddress').val();
        const userName = $('#txtManageCustomerUsername').val();
        const password = $('#txtManageCustomerPassword').val();

        var formData = new FormData();

        formData.append('name', name);
        formData.append('email', email);
        formData.append('nic', nic);
        formData.append('address', address);
        formData.append('profile', profile);
        formData.append('username', userName);
        formData.append('password', password);
        formData.append('userRole', "CUSTOMER");

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
                this.handleLoadAllCustomerTable();
                this.resetFields();
                alert('success')
            },
            error: (error) => {
                alert(error)
            }
        });
    }
    handleResetCustomerForm(event) {
        event.preventDefault();
        this.resetFields();
    }
    resetFields() {
        $('#txtManageCustomerId').val("");
        $('#txtManageCustomerName').val("");
        $('#txtManageCustomerEmail').val("");
        $('#txtManageCustomerNic').val("");
        $('#txtManageCustomerAddress').val("");
        $('#txtManageCustomerUsername').val("");
        $('#txtManageCustomerPassword').val("");
        $('#manage-cropped-image').attr('src', "");
        $('#manage-customer-img-for-crop').attr('src', "");
    }
    handleSelectCustomer(event) {
        event.preventDefault();
        const row = $(event.currentTarget.closest('tr'));
        const userId = $(event.target.closest('tr')).data('user-id');

        const name = row.find('td:nth-child(1)').text();
        const email = row.find('td:nth-child(3)').text();
        const nic = row.find('td:nth-child(2)').text();
        const address = row.find('td:nth-child(4)').text();
        const username = row.find('td:nth-child(5)').text();
        const profile = row.find('img').attr('src').split(' ')[1]

        $('#txtManageCustomerId').val(userId);
        $('#txtManageCustomerProfile').val(profile);
        $('#txtManageCustomerName').val(name);
        $('#txtManageCustomerEmail').val(email);
        $('#txtManageCustomerNic').val(nic);
        $('#txtManageCustomerAddress').val(address);
        $('#txtManageCustomerUsername').val(username);

        // $('#txtManageCustomerPassword').val();
        // document.getElementById('manage-cropped-image');
    }
    handleSaveCustomerValidation(event) {
        event.preventDefault();

        const name = $('#txtManageCustomerName').val();
        const email = $('#txtManageCustomerEmail').val();
        const nic = $('#txtManageCustomerNic').val();
        const address = $('#txtManageCustomerAddress').val();
        const profileImg = document.getElementById('manage-cropped-image');
        const userName = $('#txtManageCustomerUsername').val();
        const password = $('#txtManageCustomerPassword').val();

        try {
            var arr = profileImg.src.split(',');
            var mime = arr[0].match(/:(.*?);/)[1];
            var bstr = atob(arr[1]);
            var n = bstr.length;
            var u8arr = new Uint8Array(n);

            while (n--) {
                u8arr[n] = bstr.charCodeAt(n);
            }

            const profile = new File([u8arr], 'image.jpg', { type: mime });
            // validation

            var formData = new FormData();

            formData.append('name', name);
            formData.append('email', email);
            formData.append('nic', nic);
            formData.append('address', address);
            formData.append('profile', profile);
            formData.append('username', userName);
            formData.append('password', password);
            formData.append('userRole', "CUSTOMER");

            this.handleSaveCustomer(formData);
        } catch (e) {
            console.log(e)
            alert('please select profile picture');
            return;
        }
    }
    handleSaveCustomer(customer) {
        $.ajax({
            type: "POST",
            url: "http://localhost:8090/auth/api/v1/auth/register",
            data: customer,
            contentType: false,
            processData: false,
            success: (data) => {
                this.handleLoadAllCustomerTable();
                this.resetFields();
                alert('success')
            },
            error: (error) => {
                alert(error)
            }
        });
    }
    handleLoadAllCustomer(event) {
        event.preventDefault();
        this.handleLoadAllCustomerTable();
    }
    handleLoadAllCustomerTable() {
        $('#customer_table_body').empty();
        $.ajax({
            type: "GET",
            url: "http://localhost:8090/auth/api/v1/auth/customer",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem('token')
            },
            success: (customers) => {
                $('#customer_table_body').append(customers?.map(this.renderCustomerRow).join(''));
            }
            ,
            error: (error) => {
                console.log(error)
            }
        })
    }
    renderCustomerRow(customer) {
        console.log(customer.name)
        return `
            <tr data-user-id="${customer.userId}">
                <td class="col-lg-2">${customer.name}</td>
                <td class="col-lg-2">${customer.nic}</td>
                <td class="col-lg-2">${customer.email}</td>
                <td class="col-lg-2">${customer.address}</td>
                <td class="col-lg-1">${customer.username}</td>
                <td class="col-lg-2 text-center"><img src="data:image/jpeg;base64, ${customer.profile}" width="80%"></td>
                <td class="col-lg-1">
                    <a class="edit_icon edit_customer" href="" >
                        <i class='bx bx-edit'></i>
                    </a>
                </td>
            </tr>
        `;
    }
}
new CustomerController();