import pika, os, json
from matplotlib import pyplot as plt

font = {'family': 'serif',
        'color':  'darkred',
        'weight': 'normal',
        'size': 16,
        }
font1 = {'family': 'serif',
        'weight': 'normal',
        'size': 14,
        }
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
    gender = ['Male','Female']
    students = [size-f,f]
    plt.title('Gender wise distribution of Cases',fontdict=font)
    explode={0.1,0}
    plt.pie(students, explode = explode,labels = gender,textprops={'fontsize': 14},autopct='%1.1f%%', shadow=True, startangle=140)
    plt.savefig('ingestion/resources/images/reports/1.png')
    plt.close()
    plt.hist(age, bins=10)
    plt.xlabel('Age group',fontdict=font1)
    plt.ylabel('No. of Cases',fontdict=font1)
    plt.title('No.of Cases In Each Age Group',fontdict=font)
    plt.savefig('ingestion/resources/images/reports/4.png')
    plt.close()
    gender = ['CoMorbid and Deceased','CoMorbid and Recovered']
    students = [com_and_dead,com-com_and_dead]
    plt.title('Fatality Rate amongst CoMorbidities',fontdict=font)
    explode={0.1,0}
    plt.pie(students,explode=explode, labels = gender,textprops={'fontsize': 14},autopct='%1.1f%%', shadow=True, startangle=140)
    plt.savefig('ingestion/resources/images/reports/3.png')
    plt.close()
    plt.hist(dead, bins=10)
    plt.xlabel('Age group',fontdict=font1)
    plt.ylabel('No. of Deaths',fontdict=font1)
    plt.title('No.of Deaths In Each Age Group',fontdict=font)
    plt.savefig('ingestion/resources/images/reports/6.png')
    plt.close()
    labe=['No. of Cases','No. of Deaths']
    plt.hist([age, dead],label=labe)
    plt.xlabel('Age group',fontdict=font1)
    plt.title('No.of Cases v/s Deaths In Each Age Group',fontdict=font)
    plt.legend()
    plt.savefig('ingestion/resources/images/reports/5.png')
    plt.close()
    gender = ['Recovered','Deceased']
    students = [size-len(dead),len(dead)]
    plt.title('Recovery Rate',fontdict=font)
    explode={0.1,0}
    plt.pie(students, explode = explode,labels = gender,textprops={'fontsize': 14},autopct='%1.1f%%', shadow=True, startangle=140)
    plt.savefig('ingestion/resources/images/reports/2.png')
    plt.close()

# set up subscription on the queue
channel.basic_consume('consumer', callback, auto_ack=True)


# start consuming (blocks)
channel.start_consuming()
connection.close()
