 // import axios from "../node_modules/axios/lib/axios.js";





const allClassButton = document.getElementById("allClass")
const Data = document.getElementById('Data');
const breakButton = document.getElementById("break");
const canClassButton = document.getElementById("canClass");
const userClassButton = document.getElementById('userClass');
const userData = document.getElementById('userData');




breakButton.addEventListener('click', userBreak);
function userBreak() {
    window.location.assign('../User/break');
}


allClassButton.addEventListener('click', allFind);
function allFind() {
    Data.innerHTML = '';
    axios.post("/User","class=all&method=findClass")
        .then(res => {
            const CourseName = res.data.name;
            const CourseXF = res.data.xf;
            const CourseMax = res.data.max;

            const table = document.createElement("table");
            const headerRow = document.createElement('tr');
            const headers = ['课程名字','课程剩余人数','课程学分'];
            headers.forEach(headerText => {
                const th = document.createElement('th');
                th.textContent = headerText;
                headerRow.appendChild(th);
            });
            table.appendChild(headerRow);

            for(let i = 0; i<CourseName.length; i++) {
                const dataRow = document.createElement('tr');
                const nameCell = document.createElement('td');
                nameCell.textContent = CourseName[i];
                dataRow.appendChild(nameCell);

                const maxCell = document.createElement('td');
                maxCell.textContent = CourseMax[i];
                dataRow.appendChild(maxCell);

                const xfCell = document.createElement('td');
                xfCell.textContent = CourseXF[i];
                dataRow.appendChild(xfCell);

                table.appendChild(dataRow);
            }

            Data.appendChild(table);

        })
        .catch(error=>{console.error(error);});
}


canClassButton.addEventListener('click',canFind);
function canFind(){
    Data.innerHTML = '';
    axios.post("/User",{
        class: "can",
        method: 'FindClass'
    })
.then(res => {
    const CourseName = res.data.name;
    const CourseXF = res.data.xf;
    const CourseMax = res.data.max;
    const CourseID = res.data.id;

    const table = document.createElement("table");
    const headerRow = document.createElement('tr');
    const headers = ['课程名字', '课程剩余人数', '课程学分', '操作'];
    headers.forEach(headerText => {
        const th = document.createElement('th');
        th.textContent = headerText;
        headerRow.appendChild(th);
    });
    table.appendChild(headerRow);
    for(let i = 0; i<CourseName.length; i++) {
        const dataRow = document.createElement('tr');
        const nameCell = document.createElement('td');
        nameCell.textContent = CourseName[i];
        dataRow.appendChild(nameCell);

        const maxCell = document.createElement('td');
        maxCell.textContent = CourseMax[i];
        dataRow.appendChild(maxCell);

        const xfCell = document.createElement('td');
        xfCell.textContent = CourseXF[i];
        dataRow.appendChild(xfCell);

        const  csCell = document.createElement('td');
        const button = document.createElement('button');
        button.textContent = '选课'
        button.addEventListener('click', function(){closeClass(CourseID)});
        csCell.appendChild(button);
        dataRow.appendChild(csCell);

        table.appendChild(dataRow);
    }
    Data.appendChild(table);
}).catch(error=>{console.error(error);})
}


function closeClass(id) {
    const result = window.confirm('你确定要选该课程吗？');
    if (!result) {
        return;
    }
    axios.post("/User", {
        id : id,
        method: 'FindClass'
    })
        .then(res => {
            if (res.data === "true") {
                alert("OK");
            } else {
                alert("NO")
            }

        }).catch(error => {
        console.error(error);
    })}


    userClassButton.addEventListener('click', useClass);

    function useClass() {
        Data.innerHTML = '';
        axios.post("/User", {
            class: "user",
            method: 'FindClass'
        })
            .then(res => {
                const CourseName = res.data.name;
                const CourseXF = res.data.xf;
                const CourseID = res.data.id;

                const table = document.createElement("table");
                const headerRow = document.createElement('tr');
                const headers = ['课程名字', '课程学分', '操作'];
                headers.forEach(headerText => {
                    const th = document.createElement('th');
                    th.textContent = headerText;
                    headerRow.appendChild(th);
                });
                table.appendChild(headerRow);

                for (let i = 0; i < CourseName.length; i++) {
                    const dataRow = document.createElement('tr');
                    const nameCell = document.createElement('td');
                    nameCell.textContent = CourseName[i];
                    dataRow.appendChild(nameCell);

                    const xfCell = document.createElement('td');
                    xfCell.textContent = CourseXF[i];
                    dataRow.appendChild(xfCell);

                    const csCell = document.createElement('td');
                    const button = document.createElement('button');
                    button.textContent = '退课'
                    button.addEventListener('click', function () {
                        returnClass(CourseID[i]);
                    });
                    csCell.appendChild(button);
                    dataRow.appendChild(csCell);

                    table.appendChild(dataRow);
                }
                Data.appendChild(table);
            }).catch(error => {
            console.error(error);
        })
    }


    function returnClass(id) {
        const result = window.confirm('你确定要退该课程吗？');
        if (!result) {
            return;
        }
        axios.post("/User", {
            id: id,
            method: 'ReturnClass'
        })
            .then(res => {
                if (res.data === "true") {
                    alert("OK");
                } else {
                    alert("NO")
                }

            }).catch(error => {
            console.error(error);
        })
    }



