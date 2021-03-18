FROM python:3

COPY requirements.txt /data/requirements.txt

RUN apt-get -y update
RUN pip3 install -r /data/requirements.txt

EXPOSE 5000/tcp

CMD ["python3", "/data/app.py"]
