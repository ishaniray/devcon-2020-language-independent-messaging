import pika, os, json
from matplotlib import pyplot as plt
import numpy as np

# Access the CLODUAMQP_URL environment variable and parse it (fallback to localhost)
url = os.environ.get('CLOUDAMQP_URL', 'amqp://guest:guest@localhost:5672/%2f')
params = pika.URLParameters(url)
connection = pika.BlockingConnection(params)
channel = connection.channel() # start a channel
channel.queue_declare(queue='consumer') # Declare a queue

# create a function which is called on incoming messages
def callback(ch, method, properties, body):
    x = body.decode('utf-8')
    y = json.loads(x)
    #print(y)
    plot(y)
    
def plot(y):
    age = []
    f=0
    com=0
    com_and_dead=0
    dead=[]
    size = len(y)
    for z in y:
        age.append(z["age"])
        if (z["deceasedFlag"]=='Y'):
            dead.append(z["age"])
        if (z["coMorbiditiesFlag"]=='Y') :
            com=com+1
            if (z["deceasedFlag"]=='Y'):
                com_and_dead=com_and_dead+1
        if (z["gender"]=='F'):
            f=f+1
    fig = plt.figure()
    ax = fig.add_axes([0,0,1,1])
    ax.axis('equal')
    gender = ['Male','Female']
    students = [size-f,f]
    ax.pie(students, labels = gender,autopct='%1.2f%%')
    plt.savefig('ingestion/resources/images/reports/gender.png')
    plt.close()
    plt.hist(age, bins=10)
    plt.savefig('ingestion/resources/images/reports/cases.png')
    plt.close()
    fig = plt.figure()
    ax = fig.add_axes([0,0,1,1])
    ax.axis('equal')
    gender = ['CoMorbid and Dead','CoMorbid and Alive']
    students = [com_and_dead,com-com_and_dead]
    ax.pie(students, labels = gender,autopct='%1.2f%%')
    plt.savefig('ingestion/resources/images/reports/Comorbidity_Dead_Alive.png')
    plt.close()
    plt.hist(dead, bins=10)
    plt.savefig('ingestion/resources/images/reports/dead.png')
    plt.close()
    plt.hist([age, dead])
    plt.savefig('ingestion/resources/images/reports/cases_dead_1.png')
    plt.close()
    plt.hist(age, alpha=0.5,label='cases')
    plt.hist(dead, alpha=0.5,label='deaths')
    plt.savefig('ingestion/resources/images/reports/cases_dead_2.png')
    plt.close()

# set up subscription on the queue
channel.basic_consume('consumer', callback, auto_ack=True)


# start consuming (blocks)
channel.start_consuming()
connection.close()
