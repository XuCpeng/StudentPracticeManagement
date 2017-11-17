var province;
var city;
var county;
var ddlProvince;
var ddlCity;
var ddlCounty;

function selectProvince(parentId) {
    ddlProvince = document.getElementById("province");
    ddlCity = document.getElementById("city");
    ddlCounty=document.getElementById("county");
    $.ajax({
        url: "/j2ee/getregion?parentId=" + parentId,
        type: "GET",
        dataType:"text",
        success: function (data) {
            province=data.split(",");
            for(var i =0;i<province.length-1; i++)
            {
                var option = document.createElement("option");
                option.appendChild(document.createTextNode(province[i]));
                option.value = province[i];
                ddlProvince.appendChild(option);
            }
        }
    });

}

function indexof(strList,value)
{
    var k=0;
    for(;k<strList.length;k++)
    {
        if(strList[k] === value)
            return k;
    }
    return k;
}

function selectCity(obj){
    alert("FFFFFFF");
    ddlCity.options.length = 0;
    ddlCounty.options.length=0;//clear
    var index = indexof(province,obj.value)+2;
    $.ajax({
        url: "getregion?parentId=" + index,
        type: "POST",
        dataType:"text",
        success: function (data) {
            city=data.split(",");
            for(var i =0;i<city.length-1; i++)
            {
                var option = document.createElement("option");
                option.appendChild(document.createTextNode(city[i]));
                option.value = city[i];
                ddlCity.appendChild(option);
            }
        }
    });
}

function selectCounty() {
    ddlCounty.options.length=0;
    var citySelected=$('#city').find('option:selected').text();
    var proviceIndex=$('#province').find('option:selected').text();
    var index = indexof(province,proviceIndex)+2;
    $.ajax({
        url: "getregion?citySelected=" + citySelected+"&parentId="+index,
        type: "POST",
        dataType:"text",
        success: function (data) {
            county=data.split(",");
            for(var i =0;i<county.length-1; i++)
            {
                var option = document.createElement("option");
                option.appendChild(document.createTextNode(county[i]));
                option.value = county[i];
                ddlCounty.appendChild(option);
            }
        }
    });
}