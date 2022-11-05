FROM python:3-alpine

WORKDIR /usr/src/app

COPY requirements.txt requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

COPY runner.py ./

ENTRYPOINT ["python", "./runner.py"]
