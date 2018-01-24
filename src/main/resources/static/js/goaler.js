var TbRow = document.getElementById("goaler");
if (TbRow != null)
{
    for (var i=0;i<TbRow.rows.length ;i++ )
    {
        if (TbRow.rows[i].rowIndex%2==1)
        {
            TbRow.rows[i].style.backgroundColor="";
        }
        else
        {
            TbRow.rows[i].style.backgroundColor="#F1F1F1";
        }
    }
}