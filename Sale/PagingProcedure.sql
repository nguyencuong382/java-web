use SaleDB
go
create proc GetProducts
@u int,
@v int
as
begin
 SELECT a.ProductID,a.ProductName,a.UnitPrice FROM ( 
  SELECT *, ROW_NUMBER() OVER (ORDER BY productid) as row 
  FROM ProductTBL a
 ) a WHERE a.row >= @u and a.row <= @v
end
go
exec GetProducts 6, 15