# PowerShell script to convert HTML to PDF
# This script uses Edge browser to convert HTML to PDF

$htmlFile = "CMPE224_HW1_Report.html"
$pdfFile = "CMPE224_HW1_Report.pdf"

# Check if HTML file exists
if (Test-Path $htmlFile) {
    Write-Host "Converting HTML to PDF..."
    
    # Get full path
    $htmlPath = Resolve-Path $htmlFile
    $pdfPath = Join-Path (Get-Location) $pdfFile
    
    # Use Edge to convert HTML to PDF
    $edgePath = "${env:ProgramFiles(x86)}\Microsoft\Edge\Application\msedge.exe"
    
    if (Test-Path $edgePath) {
        # Edge command to print HTML to PDF
        & $edgePath --headless --disable-gpu --print-to-pdf="$pdfPath" "$htmlPath"
        Write-Host "PDF created: $pdfFile"
    } else {
        Write-Host "Edge not found. Please use browser method instead."
        Write-Host "1. Open $htmlFile in your browser"
        Write-Host "2. Press Ctrl+P"
        Write-Host "3. Save as PDF"
    }
} else {
    Write-Host "HTML file not found: $htmlFile"
}


