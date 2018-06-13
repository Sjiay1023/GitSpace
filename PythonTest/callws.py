#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import suds

from suds.client import Client

url = 'http://localhost:9080/RHIO/TCIService/TCIService.wsdl'
client = Client(url)

print (client)
