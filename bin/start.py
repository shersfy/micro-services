'''
启动Java程序
@Time: 2018-08-03
@Author: py
'''
import os

def main(args):
	baseDir = os.path.abspath('..')
	print(baseDir)
	jarDir = "%s/%s" % (baseDir, args[0])
