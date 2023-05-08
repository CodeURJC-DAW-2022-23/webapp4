Param(
    [String] $image
)

Set-Location ../

docker build -t scuadrosf/$image -f docker/Dockerfile .

docker push scuadrosf/$image

Set-Location ./docker

docker compose down

docker compose up

