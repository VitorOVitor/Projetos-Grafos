import matplotlib.pyplot as plt


vertices = [100, 500, 1000, 5000]
runtime_path = [2, 8, 15, 60]
runtime_complete = [20, 200, 800, 5000]

plt.figure()
plt.plot(vertices, runtime_path, marker='o', label='Path Graph')
plt.plot(vertices, runtime_complete, marker='s', label='Complete Graph')
plt.xlabel('Número de Vértices')
plt.ylabel('Tempo de Execução (ms)')
plt.title('Comparação de Runtime')
plt.legend()
plt.grid(True)
plt.savefig('runtime_comparativo.pdf')
plt.close()