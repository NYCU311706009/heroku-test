function menu() {
    window.addEventListener('touchstart',function(event){
            let leftmenu = document.getElementById("leftmenu");
            if(event.target != leftmenu && event.target.parentNode != leftmenu){
                leftmenu.style.display = 'none';
            }
    });  
    window.addEventListener('touchstart',function(event){
            let rightmenu = document.getElementById("rightmenu");
            let login_before_mobile = document.getElementById("login_before_mobile");
            if(event.target != login_before_mobile && event.target.parentNode != login_before_mobile){
                rightmenu.style.display = 'none';
            }	
    });
    window.addEventListener('touchstart',function(event){
            let rightmenu2 = document.getElementById("rightmenu_login");
            let login_after_mobile = document.getElementById("login_after_mobile");
            if(event.target != login_after_mobile && event.target.parentNode != login_after_mobile){
                rightmenu2.style.display = 'none';
            }
    }); 
}