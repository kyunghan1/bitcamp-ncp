var obj = new Object();

        obj.name = "박경한";
        obj.age = 20;
        obj.hello = function() {
            console.log("안녕하세요," + "저는" + this.age + "살 입니다");
        }

        console.log(obj);
        obj.hello();
