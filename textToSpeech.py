from gtts import gTTS
import requests

def convertTextToSpeech(body):
    language ="en"
    text = body

    speech = gTTS(text=text, lang=language, slow=False)
    speech.save(f"{body}.mp3")
