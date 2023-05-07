Param(
    [String] $image
)


Remove-Item /sq .mysql 
Set-Location ../

docker build -t scuadrosf/$image -f docker/Dockerfile .

docker push scuadrosf/$image

Set-Location ./docker

docker compose down

docker compose up

Set-Location ../frontend

npm install

ng build --configuration production --base -href="/new/"

copy .\dist\frontend\* ..\Backend\idealTrip\src\main\resources\public\new\