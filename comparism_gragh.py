
import numpy as np
import scipy.odr as odr
import scipy.constants as ct
import matplotlib.pyplot as plt
import pandas as pd
from scipy.optimize import curve_fit


data_pandas=pd.read_excel(r"/Users/fanyupei/Downloads/y2s1/sc2001_algorithm/comparism.xlsx")
print(data_pandas)
type(data_pandas)
data=np.array(data_pandas)
print(data)
pt_E1=data[0::3,:]
pt_E2=data[1::3,:]
pt_E3=data[2::3,:]

#plot E_min
fig, axs = plt.subplots()
axs.plot(pt_E1[:,0],pt_E1[:,3],marker="o",label="matrix")
axs.plot(pt_E1[:,0],pt_E1[:,2],marker="o",label="list")
axs.set_ylabel(r"time / $\rm{\mu}$s")
axs.set_xlabel(r"$V$")
plt.xscale("log")
plt.yscale("log")
axs.legend()
plt.title(r"$E=E_{\rm{min}}=V-1$")
fig.tight_layout()
plt.show()

#plot 0.5*E_max
fig, axs = plt.subplots()
axs.plot(pt_E2[:,0],pt_E2[:,3],marker="o",label="matrix")
axs.plot(pt_E2[:,0],pt_E2[:,2],marker="o",label="list")
axs.set_ylabel(r"time / $\rm{\mu}$s")
axs.set_xlabel(r"$V$")
plt.xscale("log")
plt.yscale("log")
axs.legend()
plt.title(r"$E=\frac{1}{2}E_{\rm{max}}=\frac{1}{2}V(V-1)$")
fig.tight_layout()
plt.show()

#plot E_max
fig, axs = plt.subplots()
axs.plot(pt_E3[:,0],pt_E3[:,3],marker="o",label="matrix")
axs.plot(pt_E3[:,0],pt_E3[:,2],marker="o",label="list")
axs.set_ylabel(r"time / $\rm{\mu}$s")
axs.set_xlabel(r"$V$")
plt.xscale("log")
plt.yscale("log")
axs.legend()
plt.title(r"$E=E_{\rm{max}}=V(V-1)$")
fig.tight_layout()
plt.show()

