import os

def generate_path_graph(n):
    lines = [f"{n} {n-1}"]
    for i in range(n-1):
        lines.append(f"{i} {i+1} 1")
    lines.append(f"0 {n-1}")  
    return "\n".join(lines)

def generate_complete_graph(n):
    edges = n*(n-1)//2
    lines = [f"{n} {edges}"]
    for i in range(n):
        for j in range(i+1, n):
            lines.append(f"{i} {j} 1")
    lines.append(f"0 {n-1}")  
    return "\n".join(lines)

if __name__ == "__main__":
    os.makedirs("inputs", exist_ok=True)
    tamanhos = [100, 500, 1000, 5000]

    for n in tamanhos:
        path_txt = generate_path_graph(n)
        with open(f"inputs/path_{n}.txt", "w") as f:
            f.write(path_txt)

        complete_txt = generate_complete_graph(n)
        with open(f"inputs/complete_{n}.txt", "w") as f:
            f.write(complete_txt)

    print("Arquivos gerados em ./inputs/")